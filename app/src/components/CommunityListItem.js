const CommunityListItem = ({id, title, onCommunityClick}) => {

    return (
        <div className="sidebar__list-item sidebar__list-item--hover" onClick={() => onCommunityClick(id)}>
            <div className="sidebar__list-item-logo"></div>
            <span className="sidebar__list-item-text">{title}</span>
        </div>
    );
};

export default CommunityListItem;
