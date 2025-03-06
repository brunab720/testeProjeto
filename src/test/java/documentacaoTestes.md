# Teste 1: Validar Criação de ID Único - POST

## Descrição
Este teste tem como objetivo validar que ao criar dois usuários distintos através de requisições POST, os IDs gerados para cada usuário devem ser **únicos**.

## Objetivo
Verificar que, ao realizar duas requisições POST para criar usuários, os IDs gerados não são iguais.


## Passos do Teste

1. **Preparar a requisição 1:**
    - Definir um nome de usuário (João) e um cargo ("Software Engineer").
    - Enviar a requisição POST para criar o usuário.

2. **Preparar a requisição 2:**
    - Definir um nome de usuário diferente (Bruna) e o cargo ("Software Engineer").
    - Enviar a requisição POST para criar o usuário.

3. **Verificar os IDs gerados:**
    - Extrair o `id` gerado para cada usuário da resposta.

4. **Validação:**
    - Verificar que os IDs gerados para João e Bruna são diferentes, garantindo que sejam IDs únicos.

## Implementação

```java
@Test
public void validarIDUnico() {
   // Criação de um mapa vazio para armazenar os dados do usuários
   Map<String, Object> map = new HashMap<String, Object>();

   // Corpo da Requisição
   JSONObject request1 = new JSONObject(map);
   request1.put("name", "João");  // Nome do primeiro usuário
   request1.put("job", "Software Engineer");  // Cargo do primeiro usuário

   // Definindo a URI base e realizando a requisição POST para criar o usuários
   baseURI = "https://reqres.in/api";
   Response response1 = given().
           header("Content-Type", "application/json").  
                   contentType(ContentType.JSON).  
                   body(request1).  
                   when().
           post("users").  
                   then().
           statusCode(201).  
                   log().all().  
                   extract().response(); 

   // Corpo da Requisição
   JSONObject request2 = new JSONObject(map);
   request2.put("name", "Bruna");  // Nome do segundo usuário
   request2.put("job", "Software Engineer");  // Cargo do segundo usuário

   // Realizando a requisição POST para criar o usuário Bruna
   Response response2 = given().
           header("Content-Type", "application/json").
           contentType(ContentType.JSON).
           body(request2).
           when().
           post("users").
           then().
           statusCode(201).
           log().all().
           extract().response();

   // Extraindo os IDs gerados para ambos os usuários
   String Id1 = response1.jsonPath().getString("id");  // ID gerado para João
   String Id2 = response2.jsonPath().getString("id");  // ID gerado para Bruna

   // Verificando se os IDs são diferentes, garantindo que são únicos
   assertNotEquals(Id1, Id2, "Os IDs não podem ser iguais. Devem ser IDs únicos.");
}
```


# Teste 2: Validar Campos Não Nulos e Vazios - GET

## Descrição
Este teste valida que os campos **`first_name`**, **`last_name`** e **`email`** da resposta da API **`GET`** não podem ser nulos ou vazios.

## Objetivo
Verificar que, ao fazer uma requisição **GET** à API, os campos (`first_name`, `last_name`, `email`) da resposta não sejam nulos nem vazios.

## Passos do Teste

1. **Realizar Requisição GET:**
    - Enviar uma requisição GET para a URL **`https://reqres.in/api/users?page=2`**.

2. **Verificar Status da Resposta:**
    - Confirmar que o código de status da resposta seja **200 (OK)**.

3. **Validar Campos Não Nulos:**
    - Extrair os valores dos campos **`first_name`**, **`last_name`** e **`email`** da resposta.
    - Verificar que **`first_name`** não seja nulo nem vazio.
    - Verificar que **`last_name`** não seja nulo nem vazio.
    - Verificar que **`email`** não seja nulo nem vazio.

## Implementação

```java
package apiModules;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.Map;

public class apiNulos {

    @Test
    public void validarNULOS(){

        // Realizando a requisição GET para buscar os dados dos usuários
        Response response = get("https://reqres.in/api/users?page=2");

        int statusCode = response.getStatusCode();
        
        // Validando se o código de status da resposta é 200
        Assert.assertEquals(statusCode,200);

        Map<String, Object> map = response.jsonPath().getMap("data[0]");

        // Validando que o 'first_name' não é nulo nem vazio
        String firstname = (String) map.get("first_name");
        Assert.assertNotNull(firstname, "firstname não pode ser nulo");
        Assert.assertFalse(firstname.isEmpty(), "firstname não pode ser vazio");

        // Validando que o 'last_name' não é nulo nem vazio
        String lastname = (String) map.get("last_name");
        Assert.assertNotNull(lastname, "last_name não pode ser nulo");
        Assert.assertFalse(lastname.isEmpty(), "firstname não pode ser vazio");

        // Validando que o 'email' não é nulo nem vazio
        String email = (String) map.get("email");
        Assert.assertNotNull(email, "email não pode ser nulo");
        Assert.assertFalse(email.isEmpty(), "email não pode ser vazio");

    }
}






