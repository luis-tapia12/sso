import { Link } from 'react-router-dom';
import { Route } from '../router';

type HeaderProps = {
	routes: Route[];
};

const Header = ({ routes }: HeaderProps) => {
	const renderRoute = (currRoute: Route, routeIndex: number) => (
		<li key={routeIndex}>
			<Link to={currRoute.path}>{currRoute.title}</Link>
		</li>
	);

	const renderRoutes = routes.filter((currRoute) => currRoute.title).map(renderRoute);

	return (
		<div>
			<ul>{renderRoutes}</ul>
		</div>
	);
};

export default Header;
