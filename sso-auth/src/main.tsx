import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import { NotificationProvider } from 'carbonx-ui/dist/Notification';

import router from './router';

import './index.css';
import 'carbonx-ui/dist/assets/main.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
	<React.StrictMode>
		<NotificationProvider>
			<RouterProvider router={router} />
		</NotificationProvider>
	</React.StrictMode>
);
