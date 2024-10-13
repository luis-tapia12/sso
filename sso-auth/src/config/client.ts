import axios from 'axios';

const client = axios.create({
	baseURL: `${import.meta.env.VITE_BASE_URL}/api/v1`
});

export default client;
