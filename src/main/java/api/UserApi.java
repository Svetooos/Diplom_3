package api;

import io.qameta.allure.Step;
import model.LoginResponse;
import model.User;
import util.URL;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseHttpClient {

    @Step
    public LoginResponse loginUser(User user) {
        return given()
                .spec(requestSpec())
                .body(user)
                .post(URL.API_LOGIN_USER_URL)
                .as(LoginResponse.class);
    }

    @Step
    public void deleteUser(String token) {
        given()
                .spec(requestSpec())
                .header("Authorization", token)
                .delete(URL.API_DELETE_USER_URL);
    }


    @Step()
    public LoginResponse createUser(User user) {
        return given()
                .spec(requestSpec())
                .body(user)
                .post(URL.API_CREATE_USER_URL)
                .as(LoginResponse.class);
    }
}