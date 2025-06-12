package dev.tinyjtt;

import java.util.Date;
import java.util.UUID;

public class Main {
    private static final String TEST_FILE = "tasks.json";

    public static void main(String[] args) {
        System.out.println("--- Iniciando Teste do Gerenciador de Tarefas ---");

        // 1. Criar uma nova lista de tarefas vazia
        TaskList minhaListaDeTarefas = new TaskList();
        System.out.println("\n1. Instância de TaskList criada.");

        // 2. Adicionar algumas tarefas usando os construtores disponíveis
        System.out.println("\n2. Adicionando algumas tarefas:");

        // Usando o construtor Task(String description, TaskStatus status)
        Task tarefaAprenderJackson = new Task("Aprender Jackson para JSON", TaskStatus.InProgress);
        minhaListaDeTarefas.addTask(tarefaAprenderJackson);
        System.out.println(" - Adicionada: " + tarefaAprenderJackson.getDescription());

        // Usando o construtor Task(String description)
        Task tarefaFazerCompras = new Task("Fazer compras do mercado");
        minhaListaDeTarefas.addTask(tarefaFazerCompras);
        System.out.println(" - Adicionada: " + tarefaFazerCompras.getDescription());

        Task tarefaLerLivro = new Task("Ler capítulo 5 do livro de design patterns", TaskStatus.Pending);
        minhaListaDeTarefas.addTask(tarefaLerLivro);
        System.out.println(" - Adicionada: " + tarefaLerLivro.getDescription());

        // 3. Exibir todas as tarefas atualmente na lista
        System.out.println("\n3. Tarefas na lista após adição:");
        minhaListaDeTarefas.displayTasks();

        // 4. Salvar as tarefas em um arquivo JSON
        System.out.println("\n4. Salvando as tarefas no arquivo: " + TEST_FILE);
        minhaListaDeTarefas.saveToFile();
        System.out.println("   Verifique o arquivo '" + TEST_FILE + "' no diretório do projeto.");

        // 5. Criar uma nova instância de TaskList para simular o carregamento do arquivo
        System.out.println("\n5. Criando uma nova TaskList para simular o carregamento...");
        TaskList listaCarregada = new TaskList();
        listaCarregada.loadFromFile(TEST_FILE);

        // 6. Exibir as tarefas que foram carregadas do arquivo
        System.out.println("\n6. Tarefas carregadas do arquivo:");
        listaCarregada.displayTasks();

        // 7. Modificar o status de uma tarefa e a data de atualização
        System.out.println("\n7. Modificando uma tarefa e salvando as alterações:");
        // Pegar a tarefa de "Aprender Jackson" pelo UUID
        Task tarefaParaModificar = listaCarregada.getTaskByUuid(tarefaAprenderJackson.getUuid());

        if (tarefaParaModificar != null) {
            System.out.println(" - Tarefa encontrada: '" + tarefaParaModificar.getDescription() + "'");
            tarefaParaModificar.setStatus(TaskStatus.Done); // Muda o status para Concluído
            tarefaParaModificar.setUpdatedAt(new Date()); // Atualiza a data de modificação
            System.out.println(" - Status alterado para: " + tarefaParaModificar.getStatus());
            System.out.println(" - Data de atualização: " + tarefaParaModificar.getUpdatedAt());
            listaCarregada.saveToFile(); // Salva a lista com a tarefa modificada
            System.out.println(" - Tarefas salvas novamente após a modificação.");
        } else {
            System.out.println(" - A tarefa 'Aprender Jackson' não foi encontrada para modificação.");
        }

        // 8. Carregar o arquivo novamente para verificar a modificação
        System.out.println("\n8. Carregando novamente para confirmar a modificação:");
        TaskList listaPosModificacao = new TaskList();
        listaPosModificacao.loadFromFile(TEST_FILE);
        listaPosModificacao.displayTasks();

        // 9. Remover uma tarefa da lista
        System.out.println("\n9. Removendo uma tarefa da lista:");
        UUID uuidParaRemover = tarefaFazerCompras.getUuid();
        System.out.println(" - Removendo a tarefa com UUID: " + uuidParaRemover + " (Descrição original: '" + tarefaFazerCompras.getDescription() + "')");
        listaPosModificacao.removeTask(listaPosModificacao.getTaskByUuid(uuidParaRemover));
        listaPosModificacao.saveToFile(); // Salva a lista após a remoção
        System.out.println(" - Tarefas salvas após a remoção.");

        // 10. Carregar o arquivo novamente para verificar a remoção
        System.out.println("\n10. Carregando novamente para confirmar a remoção:");
        TaskList listaPosRemocao = new TaskList();
        listaPosRemocao.loadFromFile(TEST_FILE);
        listaPosRemocao.displayTasks();

        System.out.println("\n--- Teste do Gerenciador de Tarefas Concluído com Sucesso! ---");
    }
}