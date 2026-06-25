package com.mik.tablehomework.repository;

import com.mik.tablehomework.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SendUserIdRepository {
    /**
     * Cоздать репозиторий SendUserIdRepository, конструктор которого не принимает на вход аргументов,
     * производит загрузку данных в список целых чисел из файла формата JSON send.json.
     * Имя данного файла сделать статической константой в классе Constants пакета util.
     * Файл вручную создавать не надо, изначально его вообще не будет существовать,
     * ваша программа в будущем сама будет его создавать в случае отсутствия и добавлять в него нужные ей данные в п. 6.
     * Исключение в конструкторе об отсутствии файла необходимо заигнорировать и не перебрасывать в сигнатуру
     */

    private ArrayList<Integer> list = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public SendUserIdRepository() {
        try {
            this.list = objectMapper.readValue(new File(Constants.FILENAME), new TypeReference<>() {});
        } catch (IOException ignored) {}
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void addSender(int id){
        list.add(id);
        saveFile();
    }

    public void saveFile() {
        try {
            objectMapper.writeValue(new File(Constants.FILENAME), list);
        } catch (IOException ignored) {
        }
    }
}
