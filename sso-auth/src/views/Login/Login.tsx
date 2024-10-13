import { Link } from 'react-router-dom';
import Button from 'carbonx-ui/dist/Button';
import Card from 'carbonx-ui/dist/Card';
import Input from 'carbonx-ui/dist/Input';
import Password from 'carbonx-ui/dist/Password';
import get from 'lodash/get';

import logo from '/logo.svg';
import styles from './Login.module.css';
import { useLogin } from './useLogin';
import { isNotBlank, isUsername } from '../../utils/validation';

const Login: React.FC = () => {
	const { form, handleSubmit } = useLogin();
	const {
		formState: { errors, isSubmitting }
	} = form;

	return (
		<Card className={styles.loginCard}>
			<img src={logo} alt="logo" />
			<h2>Welcome to CarbonX</h2>
			<form onSubmit={form.handleSubmit(handleSubmit)}>
				<Input
					error={get(errors, 'username.message')}
					label="Username"
					{...form.register('username', { validate: { isNotBlank, isUsername } })}
				/>
				<Password
					error={get(errors, 'password.message')}
					label="Password"
					visible
					{...form.register('password', { validate: { isNotBlank } })}
				/>
				<Button disabled={isSubmitting} type="submit">
					Sign In
				</Button>
			</form>
			<Link to="/register">Or sign up</Link>
		</Card>
	);
};

export default Login;
