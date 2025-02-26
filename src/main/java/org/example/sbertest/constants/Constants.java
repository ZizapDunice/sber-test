package org.example.sbertest.constants;

public interface Constants {

    String BEARER_PREFIX = "Bearer ";

    String HEADER_NAME = "Authorization";

    String UUID_VALIDATION = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    String EMAIL_VALIDATION = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

}