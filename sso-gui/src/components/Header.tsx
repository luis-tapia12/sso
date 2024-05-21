import { Link, useLocation } from 'react-router-dom';

import { Route } from '../router';
import styles from '../styles/Header.module.css';
import { useState } from 'react';
import { useAuth } from '../store/useAuth';

type HeaderProps = {
	routes: Route[];
};

const Header = ({ routes }: HeaderProps) => {
	const { pathname } = useLocation();
	const { user } = useAuth();
	const [showMenu, setShowMenu] = useState(false);

	const handleToggleMenu = () => {
		setShowMenu((prev) => !prev);
	};

	const renderRoute = (currRoute: Route, routeIndex: number) => (
		<li key={routeIndex}>
			<Link
				to={currRoute.path}
				className={pathname === currRoute.path ? styles.active : undefined}
			>
				{currRoute.title}
			</Link>
		</li>
	);

	const renderRoutes = routes.filter((currRoute) => currRoute.title).map(renderRoute);

	return (
		<header className={styles.header}>
			<nav>
				<div>Lilac Nexus</div>
				<ul>{renderRoutes}</ul>
			</nav>
			<div className={styles.headerMenu}>
				<button
					onClick={handleToggleMenu}
					style={{ backgroundImage: `url(${user.profilePicture})` }}
				>
					{!user.profilePicture &&
						user.firstName.substring(0, 1).toUpperCase() +
							user.lastName.substring(0, 1).toUpperCase()}
				</button>
				{showMenu && (
					<div>
						<div>
							<p>
								{user.firstName} {user.lastName}
							</p>
							<p>@{user.username}</p>
						</div>
						<div>
							<a href={import.meta.env.VITE_BASE_URL + '/logout'}>Sign Out</a>
						</div>
					</div>
				)}
			</div>
		</header>
	);
};

export default Header;
