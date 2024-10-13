import { useForm } from 'react-hook-form';
import { AxiosError } from 'axios';
import { useNotification } from 'carbonx-ui/dist/Notification';
import omit from 'lodash/omit';

import client from '../../config/client';
import { useNavigate } from 'react-router-dom';

type RegisterForm = {
	firstName: string;
	lastName: string;
	userName: string;
	password: string;
	confirm: string;
};
export const matchPasswords = (value: string, formValues: RegisterForm) => {
	if (value !== formValues.password) {
		return 'Passwords must match';
	}

	return true;
};

export const useRegister = () => {
	const navigate = useNavigate();
	const { sendMessage } = useNotification();
	const form = useForm<RegisterForm>({
		defaultValues: {
			firstName: '',
			lastName: '',
			userName: '',
			password: '',
			confirm: ''
		}
	});

	const handleSubmit = async (values: RegisterForm) => {
		try {
			await client.post('/users', omit(values, 'confirm'));

			sendMessage({ message: 'User successfully created!' });
			navigate('/');
		} catch (error) {
			if (error instanceof AxiosError) {
				sendMessage({ variant: 'error', message: error?.response?.data?.message });
			} else {
				sendMessage({ variant: 'error', message: 'Unknown error' });
			}
		}
	};

	return {
		form,
		handleSubmit
	};
};
