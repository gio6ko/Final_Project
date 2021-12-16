const bookId = document.getElementById('bookId').value

const commentContainerGroup = document.getElementById('commentsGroup');

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const commentForm = document.getElementById('commentForm')
commentForm.addEventListener("submit", handleCommentSubmit)

const allComments = []

fetch(`http://localhost:8080/api/${bookId}/comments`).then(response => response.json()).then(data => {
    for (let comment of data) {
        allComments.push(comment)
    }
    displayComments(allComments)
})

const displayComments = (comments) => {
    commentContainerGroup.innerHTML += comments.map(
        (c) => {
            return asComment(c)
        }
    ).join('')
}


function asComment(c) {
    let commentHtml = `<div id="commentContainer-${c.commentId}" class="list-group-item py-3">`

    commentHtml += `<p class="mb-1">${c.message}</p>`
    commentHtml += `<small>${c.user} (${c.created})</small>`
    commentHtml += `</div>`

    return commentHtml
}


async function handleCommentSubmit(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    // try {
    const responseData = await postFormDataAsJson({url, formData});

    commentContainerGroup.insertAdjacentHTML("afterend", asComment(responseData));
    commentContainerGroup.in

    form.reset();
    // } catch (error) {
    //
    //     let errorObj = JSON.parse(error.message);

    // if (errorObj.fieldWithErrors) {
    //     errorObj.fieldWithErrors.forEach(
    //         e => {
    //             let elementWithError = document.getElementById(e);
    //             if (elementWithError) {
    //                 elementWithError.classList.add("is-invalid");
    //             }
    //         }
    //
    //     )
    // }
    //
    // }
}


async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries());
    const formDataAsJSONString = JSON.stringify(plainFormData);

    const fetchOptions = {
        method: "POST",
        headers: {
            [csrfHeaderName]: csrfHeaderValue,
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: formDataAsJSONString
    }

    const response = await fetch(url, fetchOptions);

    // if (!response.ok) {
    //     const errorMessage = await response.text();
    //     throw new Error(errorMessage);
    // }

    return response.json();
}

