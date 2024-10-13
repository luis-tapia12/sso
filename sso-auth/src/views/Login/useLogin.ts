import { useForm } from 'react-hook-form';
import { AxiosError } from 'axios';
import { useNotification } from 'carbonx-ui/dist/Notification';

import client from '../../config/client';

type LoginForm = {
	username: string;
	password: string;
};

export const useLogin = () => {
	const { sendMessage } = useNotification();
	const form = useForm<LoginForm>({
		defaultValues: {
			username: '',
			password: ''
		}
	});

	const handleSubmit = async (values: LoginForm) => {
		const form = new FormData();
		form.append('username', values.username);
		form.append('password', values.password);

		try {
			const response = await client.post('/auth/login', form);

			window.location.href = response.data.details[0];
		} catch (error) {
			if (error instanceof AxiosError) {
				const message =
					error?.response?.data?.details?.[0] || error?.response?.data?.message;
				sendMessage({ variant: 'error', message });
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
