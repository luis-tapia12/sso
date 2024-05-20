import { createRef } from 'react';
import { createBrowserRouter } from 'react-router-dom';

import App from './App';
import Applications from './pages/Applications';
import Home from './pages/Home';
import Users from './pages/Users';
import { APPLICATIONS_PATH, HOME_PATH, USERS_PATH } from './helpers/routes';

export type Route = {
	element: JSX.Element;
	path: string;
	title?: string;
	nodeRef?: React.RefObject<HTMLDivElement>;
};

export const routes: Route[] = [
	{ element: <Home />, path: HOME_PATH, title: 'Home' },
	{ element: <Users />, path: USERS_PATH, title: 'Users' },
	{ element: <Applications />, path: APPLICATIONS_PATH, title: 'Applications' }
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
