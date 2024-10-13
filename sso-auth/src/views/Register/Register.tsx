import { Link } from 'react-router-dom';
import Button from 'carbonx-ui/dist/Button';
import Card from 'carbonx-ui/dist/Card';
import Input from 'carbonx-ui/dist/Input';
import Password from 'carbonx-ui/dist/Password';
import get from 'lodash/get';

import styles from './Register.module.css';
import { matchPasswords, useRegister } from './useRegister';
import { isName, isNotBlank, isUsername } from '../../utils/validation';

const Register: React.FC = () => {
	const { form, handleSubmit } = useRegister();
	const {
		formState: { errors }
	} = form;
	return (
		<Card title="Create an account" className={styles.registerCard}>
			<form onSubmit={form.handleSubmit(handleSubmit)}>
				<div>
					<Input
						error={get(errors, 'firstName.message')}
						label="First name"
						{...form.register('firstName', { validate: { isNotBlank, isName } })}
					/>
					<Input
						error={get(errors, 'lastName.message')}
						label="Last name"
						{...form.register('lastName', { validate: { isNotBlank, isName } })}
					/>
				</div>
				<Input
					error={get(errors, 'userName.message')}
					label="Username"
					{...form.register('userName', { validate: { isNotBlank, isUsername } })}
				/>
				<Password
					error={get(errors, 'password.message')}
					label="Password"
					visible
					{...form.register('password', { validate: { isNotBlank } })}
				/>
				<Password
					error={get(errors, 'confirm.message')}
					label="Confirm password"
					visible
					{...form.register('confirm', { validate: { isNotBlank, matchPasswords } })}
				/>
				<Button type="submit">Sign Up</Button>
			</form>
			<Link to="/">Already have an account?</Link>
		</Card>
	);
};

export default Register;
