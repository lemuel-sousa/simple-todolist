package github.lemuelsousa.com.simpletodolist;

import java.util.ArrayList;
import java.util.List;

import github.lemuelsousa.com.simpletodolist.entity.Todo;

public class TestConstants {
   
    public static final List<Todo> TODOS = new ArrayList<>() {
        {
            add(new Todo(996L, "Descansar", "dormir 8 horas", false, 0));
            add(new Todo(997L, "Descartar lixo", "Recolher lixo do quintal", true, 1));
            add(new Todo(998L, "Tomar remédios", "Tomar vitaminas depois das refeições", false, 0));
            add(new Todo(999L, "Verificar emails", "limpar spam", false, 1));
        }
      };

      public static final Todo TODO = TODOS.get(0);
}