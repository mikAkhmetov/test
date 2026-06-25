package com.mik.tablehomework.repository;

import com.mik.tablehomework.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class UserRepository {

    /**
     * 3.	Создать репозиторий, конструктор которого принимает на вход объект типа
     * File и производит инициализацию списка пользователей из этого файла. Далее
     * в этом конструкторе проставить для каждого объекта в поле isSend значение true,
     * если его id есть в списке репозитория SendUserIdRepository
     */

    private ArrayList<User> users = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public UserRepository(File file) throws IOException {
        this.users = objectMapper.readValue(file, new TypeReference<>() {});
        for (User user : users) {
            if(new SendUserIdRepository().getList().contains(user.getId())){
                user.setSend(true);
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
