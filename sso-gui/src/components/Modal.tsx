import { ReactNode } from 'react';

import styles from '../styles/Modal.module.css';

type ModalProps = {
	children: ReactNode;
	show: boolean;
	title?: string;
	onClose: () => void;
};

const Modal = ({ children, show, title, onClose }: ModalProps) => {
	return (
		show && (
			<div className={styles.modalRoot}>
				<div className={styles.modal}>
					{title && (
						<div className={styles.modalTitle}>
							<span>{title}</span>
							<button onClick={onClose}>
								<i className="fas fa-xmark" />
							</button>
						</div>
					)}
					<div className={styles.modalBody}>{children}</div>
				</div>
			</div>
		)
	);
};

export default Modal;
