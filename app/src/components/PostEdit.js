import { useState } from 'react';

function PostEdit({handleSubmitClick, handleCancelClick}) {
    
    const initalPostState = {
        title: '',
        content: ''
    }

    const [post, setPost] = useState(initalPostState)

    const handleChange = (e) => {
        const {name, value} = e.target
        setPost({...post, [name]: value})
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        const submitter = e.nativeEvent.submitter.name
        if (submitter === 'submit') {
            handleSubmitClick(post)
        }
        if (submitter === 'cancel') {
            handleCancelClick()
        }
    }

    return (
        <form className="post-edit" onSubmit={handleSubmit}>
            <textarea className="post-edit__title" rows="1" placeholder="Title" name="title" value={post.title || ''} onChange={handleChange}/>
            <textarea className="post-edit__content" rows="10" placeholder="Write your post here" name="content" value={post.content || ''} onChange={handleChange}/>
            <div className="post-edit__controllers">
                <input className="post-edit__button post-edit__button--cancel post-edit__button--cancel-hover" type="submit" name="cancel" value="Cancel" />
                <input className="post-edit__button post-edit__button--submit post-edit__button--submit-hover" type="submit" name="submit" value="Submit" />
            </div>
        </form>
    );
}

export default PostEdit;
