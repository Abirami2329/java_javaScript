function todolist() {
document.querySelector('#push').onclick = function () {
    if (document.querySelector('#newtask input').value.length == 0) {
        alert("Please Enter a Task");
    }
    else {
        const taskValue = document.querySelector('#newtask input').value
        document.querySelector('#tasks').innerHTML += `
    <div class="task" id ='${taskValue}'>
        <span id="${taskValue}">
            ${taskValue}
        </span>
        <input type="checkbox" id="${taskValue}" name="${taskValue}" value="${taskValue}" onchange='addtoCompleted(this)'>
    </div>`;
    document.getElementById('taskTile').value = '';
    }
}
}
function addtoCompleted(element){
    const spanElement = document.querySelector(`#${element.value}`);
    if(element.checked){
        spanElement.classList.add('completed');
    } else {
        spanElement.classList.remove('completed');
    }
}
function deleteCompleted(){
    const checkedElements = document.querySelectorAll('input');
    for(const element of checkedElements){
        if(element.checked){
            document.querySelector(`#${element.value}`).remove();
        }
    }
}
function emptyList(){
    const taskElements = document.querySelectorAll('div .task');
    for(const element of taskElements){
        element.remove();
    }
}
function save() {
    if (document.querySelector('#tasks').children.length !== 0){
        alert("Saved Successfully");
    } else {
        alert("Please Add Task");
    }
}