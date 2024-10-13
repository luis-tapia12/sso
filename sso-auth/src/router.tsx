import { createRef } from 'react';
import { createBrowserRouter } from 'react-router-dom';

import App from './App';
import Error from './Error';
import Login from './views/Login';
import Register from './views/Register';

export type Route = {
	element: JSX.Element;
	path: string;
	title?: string;
	nodeRef?: React.RefObject<HTMLDivElement>;
};

const HOME_PATH = '/';

export const routes: Route[] = [
	{ element: <Login />, path: '/', title: 'Sign In' },
	{ element: <Register />, path: '/register', title: 'Sign Up' }
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
			}),
			errorElement: <Error />
		}
	],
	{ basename: import.meta.env.BASE_URL }
);

export default router;
