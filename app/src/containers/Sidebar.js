import '../styles/Sidebar.css';

const Sidebar = () => {
    return (
        <div class="sidebar">
			<div class="sidebar__list">
				<span class="sidebar__list-title">COMMUNITIES</span>
				<div class="sidebar__list-content">
					<a class="sidebar__list-item sidebar__list-item--hover">
						<div class="sidebar__list-item-logo"></div>
						<span class="sidebar__list-item-text"></span>
					</a>
				</div>
			</div>
		</div>
    );
};

export default Sidebar;
