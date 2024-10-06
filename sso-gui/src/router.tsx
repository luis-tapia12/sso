import { createRef } from 'react';
import { createBrowserRouter } from 'react-router-dom';

import App from './App';
import Applications from './views/Applications';
import Permissions from './views/Permissions';
import Roles from './views/Roles';
import Users from './views/Users';

export type Route = {
	element: JSX.Element;
	path: string;
	title?: string;
	nodeRef?: React.RefObject<HTMLDivElement>;
};

const HOME_PATH = '/';

export const routes: Route[] = [
	{ element: <Applications />, path: '/applications', title: 'Applications' },
	{ element: <Permissions />, path: '/permissions', title: 'Permissions' },
	{ element: <Roles />, path: '/roles', title: 'Roles' },
	{ element: <Users />, path: '/users', title: 'Users' }
];

routes.forEach((currRoute: Route) => {
	currRoute.nodeRef = createRef<HTMLDivElement>();
});

const router = createBrowserRouter(
	[
		{
			path: HOME_PATH,
			element: <App />,
			children: routes.map(({ element, path }) => {
				const index = path === HOME_PATH;
				return {
					element,
					index,
					path: index ? undefined : path
				};
			})
		}
	],
	{ basename: import.meta.env.BASE_URL }
);

export default router;
