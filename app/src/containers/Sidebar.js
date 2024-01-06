import CommunitiesList from '../components/CommunitiesList';
import '../styles/Sidebar.css';

const Sidebar = ({communities, onCommunityClick}) => {
    return (
        <div className="sidebar">
			<CommunitiesList communities={communities} onCommunityClick={onCommunityClick}/>
		</div>
    );
};

export default Sidebar;
