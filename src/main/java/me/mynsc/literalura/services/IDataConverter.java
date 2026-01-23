package me.mynsc.literalura.services;

public interface IDataConverter {
    <T>T getData(String json, Class<T> classT);
}