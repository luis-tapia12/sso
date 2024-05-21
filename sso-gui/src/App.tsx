import { Suspense, useEffect } from 'react';
import { useLocation, useOutlet } from 'react-router-dom';
import { CSSTransition, SwitchTransition } from 'react-transition-group';

import { routes } from './router';
import Footer from './components/Footer';
import Header from './components/Header';
import Loading from './components/Loading';
import client from './config/client';
import { useAuth } from './store/useAuth';

const APP_TITLE = 'Lilac Nexus';

const App = () => {
	const { pathname } = useLocation();
	const currentOutlet = useOutlet();
	const store = useAuth();
	const { nodeRef, title } = routes.find(({ path }) => path === pathname) ?? {};

	useEffect(() => {
		document.title = `${APP_TITLE} | ${title}` || APP_TITLE;
	}, [title]);

	useEffect(() => {
		client
			.get('/auth/user')
			.then(({ data }) => {
				store.setUser(data);
			})
			.finally(() => {
				store.setLoading(false);
			});
	}, []); // eslint-disable-line

	if (store.loading) return <Loading />;

	return (
		<>
			<Header routes={routes} />
			<div className="root">
				<SwitchTransition>
					<CSSTransition
						key={pathname}
						nodeRef={nodeRef}
						timeout={300}
						classNames="fade"
						unmountOnExit
					>
						<Suspense fallback={<Loading />}>
							<div ref={nodeRef} className="main">
								{currentOutlet}
							</div>
						</Suspense>
					</CSSTransition>
				</SwitchTransition>
				<Footer />
			</div>
		</>
	);
};

export default App;
