import { useOutlet } from 'react-router-dom';

const App = () => {
	const currentOutlet = useOutlet();

	return currentOutlet;
};

export default App;
