import { Link } from 'react-router-dom';
import { Route } from '../router';

type HeaderProps = {
	routes: Route[];
};

const Header = ({ routes }: HeaderProps) => {
	const renderRoute = (route: Route, routeIndex: number) => (
		<li key={routeIndex}>
			<Link to={route.path}>{route.title}</Link>
		</li>
	);

	const renderRoutes = routes.filter((route) => route.title).map(renderRoute);

	return (
		<div>
			<ul>{renderRoutes}</ul>
		</div>
	);
};

export default Header;
