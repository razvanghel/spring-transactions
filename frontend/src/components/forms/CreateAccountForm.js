import React, { useState } from 'react';
import axios from 'axios';

const CreateAccountForm = ({ onAccountCreated }) => {
    const [userId, setUserId] = useState('');
    const [balance, setBalance] = useState(0);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/accounts', {
                customerId: userId,
                balance: balance
            });
            if (response.status === 201) {
                onAccountCreated();
            } else {
                alert('Failed to create account');
            }
        } catch (error) {
            console.error('There was an error creating the account!', error);
            alert('Failed to create account');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>User ID:</label>
                <input
                    type="text"
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>Balance:</label>
                <input
                    type="number"
                    value={balance}
                    onChange={(e) => setBalance(parseFloat(e.target.value))}
                    required
                />
            </div>
            <button type="submit">Create Account</button>
        </form>
    );
};

export default CreateAccountForm;
