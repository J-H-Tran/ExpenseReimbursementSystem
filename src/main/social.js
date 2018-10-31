// ==================== Helpers =====================
const postsAPI = 'https://jsonplaceholder.typicode.com/posts';
const commentsAPI = 'https://jsonplaceholder.typicode.com/comments';

// Global Data for storage
var posts = [];
var commentsForPosts = {};

function fetchPosts() {
    fetch(postsAPI).then(
        (newPosts) => newPosts.json()
    ).then((json) => {
        posts = json;
        renderPosts(json);
    });
}

function fetchCommentsForPost(postId) {
    fetch(`${commentsAPI}?postId=${postId}`).then(
        (newComments) => newComments.json()
    ).then((json) => {
        commentsForPosts[postId] = json;
        console.log(commentsForPosts);
        renderCommentForPost(postId, json);
    });
}


function renderPosts(comments) {
    const postsDiv = document.getElementById('posts');

    for (post of posts) {
        const postNode = document.createElement('div');
        const headingNode = document.createElement('h4');
        headingNode.setAttribute('id', `post ${post.id}`);
        const textNode = document.createTextNode(post.title);

        headingNode.appendChild(textNode);
        postNode.appendChild(headingNode);

        postsDiv.appendChild(postNode);
    }
}

function renderCommentForPost(postId, comments) {
    const post = document.getElementById(`post ${postId}`);
    const list = document.createElement('ul');

    for (comment of comments) {
        const listItemNode = document.createElement('li');
        listItemNode.setAttribute('id', `comment ${comment.id}`);
        const textNode = document.createTextNode(comment.name);

        listItemNode.appendChild(textNode);
        list.appendChild(listItemNode);
    }

    post.appendChild(list);
}

// ================= Set up Webpage =====================

window.onload = () => {
    // Render the current list of things
    
    // Set up the post list
    document.getElementById('posts').addEventListener('click', (e) => {
        if (e.target !== e.currentTarget) {
            const postId = e.target.id.split(' ')[1];
            fetchCommentsForPost(postId);
        }
        e.stopPropagation();
    }, false);
}


fetchPosts();