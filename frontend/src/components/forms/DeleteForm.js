import React, { useState } from 'react';
import axios from 'axios';

const DeleteForm = ({ onEntityDeleted }) => {
    const [entityType, setEntityType] = useState('user');
    const [entityId, setEntityId] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            let url;
            switch (entityType) {
                case 'user':
                    url = `/users/${entityId}`;
                    break;
                case 'account':
                    url = `accounts/${entityId}`;
                    break;
                case 'transaction':
                    url = `/transactions/${entityId}`;
                    break;
                default:
                    throw new Error('Invalid entity type');
            }

            const response = await axios.delete(url);
            if (response.status === 200) {
                onEntityDeleted();
                setEntityId('');
                alert(`${entityType.charAt(0).toUpperCase() + entityType.slice(1)} deleted successfully`);
            } else {
                alert(`Failed to delete ${entityType}`);
            }
        } catch (error) {
            alert(`Failed:  ${error.response.data}`);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Entity Type:</label>
                <select value={entityType} onChange={(e) => setEntityType(e.target.value)}>
                    <option value="user">User</option>
                    <option value="account">Account</option>
                    <option value="transaction">Transaction</option>
                </select>
            </div>
            <div>
                <label>Entity ID:</label>
                <input
                    type="text"
                    value={entityId}
                    onChange={(e) => setEntityId(e.target.value)}
                    required
                />
            </div>
            <button type="submit">Delete</button>
        </form>
    );
};

export default DeleteForm;
