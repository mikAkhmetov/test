package com.mik.tablehomework.config;

import java.util.ResourceBundle;

public class LogConfig {

    private static System.Logger logger = new System.Logger() {
        @Override
        public String getName() {
            return "";
        }

        @Override
        public boolean isLoggable(Level level) {
            return false;
        }

        @Override
        public void log(Level level, ResourceBundle resourceBundle, String s, Throwable throwable) {

        }

        @Override
        public void log(Level level, ResourceBundle resourceBundle, String s, Object... objects) {

        }
    };
}
