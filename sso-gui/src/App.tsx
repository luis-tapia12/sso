import { Suspense } from 'react';
import { useLocation, useOutlet } from 'react-router-dom';
import { CSSTransition, SwitchTransition } from 'react-transition-group';

import { routes } from './router';
import Footer from './components/Footer';
import Header from './components/Header';
import Loading from './components/Loading';

const App = () => {
	const { pathname } = useLocation();
	const currentOutlet = useOutlet();

	const { nodeRef } = routes.find(({ path }) => path === pathname) ?? {};

	return (
		<>
			<Header routes={routes} />
			<SwitchTransition>
				<CSSTransition
					key={pathname}
					nodeRef={nodeRef}
					timeout={300}
					classNames="fade"
					unmountOnExit
				>
					<Suspense fallback={<Loading />}>
						<div ref={nodeRef}>{currentOutlet}</div>
					</Suspense>
				</CSSTransition>
			</SwitchTransition>
			<Footer />
		</>
	);
};

export default App;
