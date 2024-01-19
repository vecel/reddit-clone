function PostEdit() {
    return (
        <form className="post-edit">
            <textarea className="post-edit__area" rows="10" placeholder="Write your post here"></textarea>
            <div className="post-edit__controllers">
                <input className="post-edit__button post-edit__button--cancel post-edit__button--cancel-hover" type="submit" value="Cancel" />
                <input className="post-edit__button post-edit__button--submit post-edit__button--submit-hover" type="submit" value="Submit" />
            </div>
        </form>
    );
}

export default PostEdit;
