import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react-swc';
import { viteMockServe } from 'vite-plugin-mock';

// https://vitejs.dev/config/
// eslint-disable-next-line
// @ts-ignore
export default defineConfig(({ mode }) => {
	const env = loadEnv(mode, process.cwd(), '');
	return {
		base: env.VITE_BASE_URL + '/app',
		plugins: [
			react(),
			viteMockServe({
				mockPath: 'mock',
				enable: true,
				watchFiles: true
			})
		],
		server: {
			proxy: {
				'/sso/api': 'http://localhost:8080'
			}
		}
	};
});
