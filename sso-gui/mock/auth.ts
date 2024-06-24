import { MockMethod } from 'vite-plugin-mock';
import type { User } from '../src/store/useUsers';

const loggedUser: User = {
	id: '1',
	username: 'admin',
	firstName: 'John',
	lastName: 'Doe',
	city: 'California',
	dateOfBirth: '1997-02-02',
	gender: 'M',
	phone: '+52 55 6089 3632'
};

export default [
	{
		url: '/sso/api/v1/auth/user',
		method: 'get',
		response: () => {
			return loggedUser;
		}
	}
] as MockMethod[];
