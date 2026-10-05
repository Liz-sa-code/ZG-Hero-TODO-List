let tasks = [];

let editingTaskId = null;


// CARREGAR TAREFAS

function loadTasks() {

    const savedTasks = localStorage.getItem("tasks");

    if (savedTasks) {
        tasks = JSON.parse(savedTasks);
    }

    renderTasks();
}


// SALVAR TAREFAS

function saveTasks() {

    localStorage.setItem("tasks", JSON.stringify(tasks));
}

// GERAR ID

function generateId() {

    if (tasks.length === 0) {
        return 1;
    }

    return Math.max(...tasks.map(task => task.id)) + 1;
}

// CRIAR TAREFA

function createTask(title, description, status) {

    const newTask = {

        id: generateId(),

        title: title,

        description: description,

        status: status

    };

    tasks.push(newTask);

    saveTasks();

    renderTasks();
}

// ATUALIZAR TAREFA


function updateTask(id, title, description, status) {

    const task = tasks.find(task => task.id === id);

    if (!task) {
        return;
    }

    task.title = title;

    task.description = description;

    task.status = status;

    saveTasks();

    renderTasks();
}

// EXCLUIR TAREFA

function deleteTask(id) {

    const confirmation = confirm(
        "Tem certeza que deseja excluir esta tarefa?"
    );

    if (!confirmation) {
        return;
    }

    tasks = tasks.filter(task => task.id !== id);

    saveTasks();

    renderTasks();
}


// EDITAR TAREFA

function editTask(id) {

    const task = tasks.find(task => task.id === id);

    if (!task) {
        return;
    }

    editingTaskId = id;

    document.getElementById("task-id").value = task.id;

    document.getElementById("title").value = task.title;

    document.getElementById("description").value = task.description;

    document.getElementById("status").value = task.status;

    document.getElementById("form-title").textContent =
        "Editar tarefa";

    document
        .getElementById("cancel-button")
        .classList
        .remove("hidden");

    document.getElementById("title").focus();

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}

// CANCELAR EDIÇÃO

function cancelEdit() {

    editingTaskId = null;

    document.getElementById("task-form").reset();

    document.getElementById("form-title").textContent =
        "Nova tarefa";

    document
        .getElementById("cancel-button")
        .classList
        .add("hidden");
}

// RENDERIZAR TAREFAS

function renderTasks() {

    const taskList = document.getElementById("task-list");

    const emptyMessage = document.getElementById("empty-message");

    const filter = document.getElementById("filter").value;

    taskList.innerHTML = "";

    let filteredTasks = tasks;

    if (filter !== "ALL") {

        filteredTasks = tasks.filter(
            task => task.status === filter
        );

    }

    if (filteredTasks.length === 0) {

        emptyMessage.style.display = "block";

    } else {

        emptyMessage.style.display = "none";

        filteredTasks.forEach(task => {

            const row = document.createElement("tr");

            row.innerHTML = `

                <td>${task.id}</td>

                <td>
                    <strong>${escapeHtml(task.title)}</strong>
                </td>

                <td>
                    ${escapeHtml(task.description)}
                </td>

                <td>
                    <span class="status ${getStatusClass(task.status)}">
                        ${task.status}
                    </span>
                </td>

                <td>

                    <div class="actions">

                        <button
                            class="btn btn-edit"
                            onclick="editTask(${task.id})"
                        >
                            Editar
                        </button>

                        <button
                            class="btn btn-delete"
                            onclick="deleteTask(${task.id})"
                        >
                            Excluir
                        </button>

                    </div>

                </td>

            `;

            taskList.appendChild(row);

        });
    }

    updateStatistics();
}

// CLASSE DO STATUS

function getStatusClass(status) {

    switch (status) {

        case "TODO":
            return "status-todo";

        case "DOING":
            return "status-doing";

        case "DONE":
            return "status-done";

        default:
            return "";
    }
}

// ATUALIZAR CONTADORES

function updateStatistics() {

    const total = tasks.length;

    const todo = tasks.filter(
        task => task.status === "TODO"
    ).length;

    const doing = tasks.filter(
        task => task.status === "DOING"
    ).length;

    const done = tasks.filter(
        task => task.status === "DONE"
    ).length;

    document.getElementById("total-count").textContent = total;

    document.getElementById("todo-count").textContent = todo;

    document.getElementById("doing-count").textContent = doing;

    document.getElementById("done-count").textContent = done;
}


// ========================================
// PROTEÇÃO CONTRA HTML
// ========================================

function escapeHtml(text) {

    const div = document.createElement("div");

    div.textContent = text;

    return div.innerHTML;
}


// SUBMIT DO FORMULÁRIO


document
    .getElementById("task-form")
    .addEventListener("submit", function(event) {

        event.preventDefault();

        const title =
            document.getElementById("title").value.trim();

        const description =
            document.getElementById("description").value.trim();

        const status =
            document.getElementById("status").value;

        if (!title || !description) {

            alert("Preencha todos os campos.");

            return;
        }


        // EDIÇÃO

        if (editingTaskId !== null) {

            updateTask(
                editingTaskId,
                title,
                description,
                status
            );

            alert("Tarefa atualizada com sucesso.");

        }

        // CRIAÇÃO

        else {

            createTask(
                title,
                description,
                status
            );

            alert("Tarefa criada com sucesso.");

        }

        cancelEdit();

    });

// BOTÃO CANCELAR

document
    .getElementById("cancel-button")
    .addEventListener("click", function() {

        cancelEdit();

    });

// FILTRO

document
    .getElementById("filter")
    .addEventListener("change", function() {

        renderTasks();

    });

// INICIALIZAÇÃO

loadTasks();