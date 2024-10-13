import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react-swc';

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
	const env = loadEnv(mode, process.cwd(), '');
	return {
		base: env.VITE_BASE_URL + '/app',
		plugins: [react()],
		server: {
			proxy: {
				[env.VITE_BASE_URL + '/api']: {
					target: 'http://localhost:8080',
					rewriteWsOrigin: true
				}
			}
		}
	};
});

