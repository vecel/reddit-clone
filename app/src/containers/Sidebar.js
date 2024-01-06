import CommunitiesList from '../components/CommunitiesList';
import '../styles/Sidebar.css';

const Sidebar = (props) => {
    return (
        <div className="sidebar">
			<CommunitiesList communities={props.communities} />
		</div>
    );
};

export default Sidebar;
