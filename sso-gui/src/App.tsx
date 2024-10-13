import { useLocation, useNavigate, useOutlet } from 'react-router-dom';
import { Sidebar } from 'carbonx-ui';

import logo from '/logo.svg';

const App = () => {
	const navigate = useNavigate();
	const currentOutlet = useOutlet();
	const { pathname } = useLocation();

	const to = (value: string) => () => {
		navigate(value);
	};

	const topMenu = [
		{
			active: pathname === '/',
			icon: 'fas fa-chart-simple',
			label: 'Dashboard',
			onClick: to('/')
		},
		{
			active: pathname === '/users',
			icon: 'fas fa-users',
			label: 'Users',
			onClick: to('users')
		},
		{
			active: pathname === '/applications',
			icon: 'fas fa-layer-group',
			label: 'Applications',
			onClick: to('applications')
		},
		{
			active: pathname === '/roles',
			icon: 'fas fa-building-shield',
			label: 'Roles',
			onClick: to('roles')
		}
	];

	const bottomMenu = [{ icon: 'fas fa-right-from-bracket', label: 'Logout', onClick: () => {} }];

	const renderLogo = <img className="icon" src={logo} />;

	return (
		<Sidebar bottomMenu={bottomMenu} topMenu={topMenu} title="CarbonX - SSO" logo={renderLogo}>
			{currentOutlet}
		</Sidebar>
	);
};

export default App;
