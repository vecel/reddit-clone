import { useState, useEffect } from "react";
import Header from "../containers/Header";
import Sidebar from "../containers/Sidebar";


function App() {

  const [communities, setCommunities] = useState([])

  useEffect(() => {
    fetch('http://localhost:8080/api/communities')
      .then(response => response.json())
      .then(data => setCommunities(data))
  }, [])


  return (
    <>
      <Header />
      <Sidebar communities={communities}/>
    </>
  );
}

export default App;
