import { ReactNode, createContext, useState } from "react";

type AuthProps = {
    children: ReactNode;
    
}

const AuthContext = createContext({});

export const AuthProvider = ({ children }: AuthProps) => {
    const [auth, setAuth] = useState({});

    return (
        <AuthContext.Provider value={{ auth, setAuth }}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthContext;