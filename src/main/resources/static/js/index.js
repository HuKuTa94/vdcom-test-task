// ------------------------------------------------ INITIALIZATION -----------------------------------------------------
const url = 'http://localhost:8000/todo';
let newTodoFile;
let newTodoText;
let newTodoCheckbox;
let todoList;
let todoListTitle;

function onload() {
    newTodoFile = document.getElementById( 'new-todo-file' );
    newTodoText = document.getElementById( 'new-todo-text' );
    newTodoCheckbox = document.getElementById( 'new-todo-checkbox' );
    todoList = document.getElementById( 'todo-list');
    todoListTitle = document.getElementById( 'todo-list-title');
    getTodoListFromServer();
}

// ---------------------------------------------- REQUEST API METHODS --------------------------------------------------
function sendRequestAndGetResponse( url, init ) {
    return fetch( url, init )
        .then( responseBody => {
            return responseBody.json();
        });
}

function displayResultFromResponse( response ) {
    // Response as array
    if( Array.isArray( response )) {
        for( let i = 0; i < response.length; i++ ) {
            addTodoElementInDOM( response[ i ]);
        }
    }
    // Response as single element
    else {
        addTodoElementInDOM( response );
    }
    setTodoListTitle();
}

function getTodoListFromServer() {
    sendRequestAndGetResponse( url, { method: 'GET' })
        .then( response => {
            displayResultFromResponse( response );
    })
}

function addTodoFromFile() {
    let file = newTodoFile.files[ 0 ];
    let formData = new FormData();
    formData.append( "file", file );

    sendRequestAndGetResponse( url + '/file', {
        method: 'POST',
        body: formData
    }).then( response => {
        displayResultFromResponse( response );
        newTodoFile.value = '';
    })
}

function addNewTodo() {
    const data = {
        text: newTodoText.value,
        done: newTodoCheckbox.checked,
        id: null
    };
    sendRequestAndGetResponse( url, {
        method: 'POST',
        body: JSON.stringify( data ),
        headers: { 'Content-Type': 'application/json' }
    }).then( response => {
        displayResultFromResponse( response );
        newTodoText.value = '';
        newTodoCheckbox.checked = false;
    })
}

function updateTodoById( id ) {
    let todo = findTodoDivById( id );
    if( todo === null ) {
        return;
    }

    let textInput = todo.children.namedItem( 'todo-text' );
    let checkboxInput = todo.children.namedItem( 'todo-checkbox' );
    const data = {
        text: textInput.value,
        done: checkboxInput.checked
    };
    const request = url + '/' + id;
    fetch( request, {
        method: 'PUT',
        body: JSON.stringify( data ),
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

function deleteTodoById( id ) {
    let todo = findTodoDivById( id );
    const request = url + '/' + id;
    fetch( request, { method: 'DELETE' })
        .then( () => {
            todo.remove();
            setTodoListTitle();
        });
}

// --------------------------------------------------- DOM METHODS -----------------------------------------------------
function addTodoElementInDOM( data )
{
    let div = document.createElement( 'div' );
    div.id = data.id;

    let textInput = document.createElement( 'input' );
    textInput.setAttribute( 'name', 'todo-text' );
    textInput.setAttribute( 'type', 'text');
    textInput.setAttribute( 'value', data.text );
    textInput.setAttribute( 'size', '40' );

    let checkboxInput = document.createElement( 'input' );
    checkboxInput.setAttribute( 'name', 'todo-checkbox' );
    checkboxInput.setAttribute( 'type', 'checkbox' );
    checkboxInput.checked = data.done;

    let updateButton = document.createElement( 'button' );
    updateButton.textContent = 'Сохранить';
    updateButton.onclick = () => updateTodoById( div.id );

    let deleteButton = document.createElement( 'button' );
    deleteButton.textContent = 'Удалить';
    deleteButton.onclick = () => deleteTodoById( div.id );

    div.appendChild( textInput );
    div.appendChild( checkboxInput );
    div.appendChild( updateButton );
    div.appendChild( deleteButton );
    todoList.appendChild( div );
}

function findTodoDivById( id ) {
    const elements = todoList.children;
    for( let i = 0; i < elements.length; i++ ) {
        let todo = elements[ i ];
        if( todo.getAttribute( 'id' ) === id ) {
            return todo;
        }
    }
    return null;
}

function isTodoListEmpty() {
    return todoList.children.length === 0;
}

function setTodoListTitle() {
    if( isTodoListEmpty() ) {
        todoListTitle.textContent = 'Список дел пуст';
    } else {
        todoListTitle.textContent = 'Список дел (' + todoList.children.length + '):';
    }
}