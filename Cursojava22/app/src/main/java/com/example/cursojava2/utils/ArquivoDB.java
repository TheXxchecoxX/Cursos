package com.example.cursojava2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ArquivoDB {

    private Context context;
    private SharedPreferences sp;
    private final String NOME_ARQUIVO = "arquivoPreferencias";

    public ArquivoDB(Context context) throws Exception {
        if (context == null){
            throw new Exception("Pasar context para utilizar ArquivoDB");
        }
        sp = context.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
    }

    //metodo para escribir un cambio
    public void escreverChave(String key,String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();

    }

    //metodo para leer un chave
    public String lerChave(String key){
        return sp.getString(key, "");
    }

    //metodo para validar un Chave
    public Boolean validarChave(String key){
        return sp.contains(key);
    }

    //metodo para leer varias chaves
    public HashMap<String, String> lerTodasAsChaves(String[] keys){
        HashMap <String, String> map = new HashMap<>();
        for (String chave : keys){
            map.put(chave, lerChave(chave));
        }
        return map;
    }

    //metodo para varias chaves
    public void escreverTodasAsChaves(HashMap<String, String> map){
        for (Map.Entry<String, String> entry : map.entrySet()){
            escreverChave(entry.getKey(), entry.getValue());
        }
    }

    //metodo para validar un arquivo de preferencias com as chaves
    public boolean validarTodasAsChave(String[] keys){
        //d
        HashSet<Boolean> set = new HashSet<>();
        for (String chave : keys){
            if (validarChave(chave)){
                set.add(true);
            }else {
                set.add(false);
            }
        }
        /*Por tanto
        SE o set possuir tamancho 1
        E SE o set contain o valor  TRUE
        Significa que todas las chaves estan presentes

        se alguna chave no existe no set seteariamos por ejemplo
        true = false com size ==2

        */
        return (set.size() == 1 && set.contains(true));
    }

}