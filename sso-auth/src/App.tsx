import { useLocation, useOutlet } from 'react-router-dom';
import { CSSTransition, SwitchTransition } from 'react-transition-group';

import { routes } from './router';
import { useEffect } from 'react';

const APP_TITLE = 'CarbonX';

const App = () => {
	const { pathname } = useLocation();
	const currentOutlet = useOutlet();
	const { nodeRef, title } = routes.find(({ path }) => path === pathname) ?? {};

	useEffect(() => {
		document.title = title ? `${APP_TITLE} | ${title}` : APP_TITLE;
	}, [title]);

	return (
		<SwitchTransition>
			<CSSTransition
				key={pathname}
				nodeRef={nodeRef}
				timeout={300}
				classNames="fade"
				unmountOnExit
			>
				<div ref={nodeRef}>{currentOutlet}</div>
			</CSSTransition>
		</SwitchTransition>
	);
};

export default App;
