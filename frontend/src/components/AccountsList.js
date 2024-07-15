
import React, { useState, useEffect } from 'react';
import TransactionList from './TransactionList.js';

const AccountsList = ({ accounts }) => {
    const [selectedAccount, setSelectedAccount] = useState(null);

    return (
           <div>
               <h3>Accounts</h3>
               {accounts.length > 0 ? (
                   <table>
                       <thead>
                           <tr>
                               <th>Account ID</th>
                               <th>Balance</th>
                               <th>Actions</th>
                           </tr>
                       </thead>
                       <tbody>
                           {accounts.map(account => (
                               <tr key={account.id}>
                                   <td>{account.id}</td>
                                   <td>{account.balance}</td>
                                   <td>
                                       <button onClick={() => setSelectedAccount(account.id)}>
                                           Show Transactions
                                       </button>
                                   </td>
                               </tr>
                           ))}
                       </tbody>
                   </table>
               ) : (
                   <p>No accounts found.</p>
               )}
               {selectedAccount && <TransactionList accountId={selectedAccount} />}
           </div>
       );
};

export default AccountsList;
