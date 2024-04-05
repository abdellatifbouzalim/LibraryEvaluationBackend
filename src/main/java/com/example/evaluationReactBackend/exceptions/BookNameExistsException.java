package com.example.evaluationReactBackend.exceptions;


public class BookNameExistsException extends RuntimeException {

        public BookNameExistsException(String message) {
            super(message);
        }
    }