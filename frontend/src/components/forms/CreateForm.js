import React, { useState } from 'react';
import axios from 'axios';

const CreateForm = ({ onCreated }) => {
    const [type, setEntityType] = useState('user');

    const [formData, setFormData] = useState({
        userId: '',
        balance: 0,
        recipientAccountId: '',
        amount: '',
        name: '',
        surname: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            let response;
            if (type === 'account') {
                response = await axios.post('/accounts', {
                    customerId: formData.userId,
                    balance: formData.balance
                });
            } else if (type === 'transaction') {
                response = await axios.post('/transactions', {
                    accountId: formData.recipientAccountId,
                    amount: parseFloat(formData.amount)
                });
            } else if (type === 'user') {
                response = await axios.post('/users', {
                    name: formData.name,
                    surname: formData.surname
                });
            }

            if (response.status === 201) {
                onCreated();
                setFormData({
                    userId: '',
                    balance: 0,
                    recipientAccountId: '',
                    amount: '',
                    name: '',
                    surname: ''
                });
            } else {
                alert(`Failed to create ${type}`);
            }
        } catch (error) {
            console.error(`There was an error creating the ${type}!`, error);
            alert(`Failed:  ${error.response.data}`);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
             <div>
                <label>Entity Type:</label>
                <select value={type} onChange={(e) => setEntityType(e.target.value)}>
                    <option value="user">User</option>
                    <option value="account">Account</option>
                    <option value="transaction">Transaction</option>
                </select>
            </div>
            {type === 'account' && (
                <>
                    <div>
                        <label>User ID:</label>
                        <input
                            type="text"
                            name="userId"
                            value={formData.userId}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div>
                        <label>Balance:</label>
                        <input
                            type="number"
                            name="balance"
                            value={formData.balance}
                            onChange={(e) => handleChange(e)}
                            required
                        />
                    </div>
                </>
            )}

            {type === 'transaction' && (
                <>
                    <div>
                        <label>Recipient Account ID:</label>
                        <input
                            type="text"
                            name="recipientAccountId"
                            value={formData.recipientAccountId}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div>
                        <label>Amount:</label>
                        <input
                            type="number"
                            name="amount"
                            value={formData.amount}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </>
            )}

            {type === 'user' && (
                <>
                    <div>
                        <label>Name:</label>
                        <input
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div>
                        <label>Surname:</label>
                        <input
                            type="text"
                            name="surname"
                            value={formData.surname}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </>
            )}

            <button type="submit">Create {type.charAt(0).toUpperCase() + type.slice(1)}</button>
        </form>
    );
};

export default CreateForm;
