import { useState } from 'react';

function PostEdit({handleSubmitClick, handleCancelClick}) {
    
    const [post, setPost] = useState('')

    const handleChange = (event) => {
        setPost(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault()
        const submitter = event.nativeEvent.submitter.name
        if (submitter === 'submit') {
            handleSubmitClick(post)
        }
        if (submitter === 'cancel') {
            handleCancelClick()
        }
    }

    return (
        <form className="post-edit" onSubmit={handleSubmit}>
            <textarea className="post-edit__area" rows="10" placeholder="Write your post here" value={post || ''} onChange={handleChange}/>
            <div className="post-edit__controllers">
                <input className="post-edit__button post-edit__button--cancel post-edit__button--cancel-hover" type="submit" name="cancel" value="Cancel" />
                <input className="post-edit__button post-edit__button--submit post-edit__button--submit-hover" type="submit" name="submit" value="Submit" />
            </div>
        </form>
    );
}

export default PostEdit;
