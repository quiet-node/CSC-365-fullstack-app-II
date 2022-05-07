import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Clusters from './pages/Clusters';
import Landing from './pages/Landing';
import YelpData from './pages/YelpData';

/**
 * @author: Nam (Logan) Nguyen
 * @college: SUNY Oswego
 * @since Spring 2022
 * @version 2.0
 * @link: https://github.com/lgad31vn/CSC-365
 */

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Landing />} />
        <Route path='/yelp-data' element={<YelpData />} />
        <Route path='/clusters' element={<Clusters />} />
      </Routes>
    </Router>
  );
}

export default App;
