import axios from 'axios';
import { useEffect, useRef, useState } from 'react';
import Header from '../components/Header';
import { select } from 'd3';

const Clusters = () => {
  const [d3data, setD3data] = useState<any>();
  const svgRef = useRef(null);
  const width = document.body.clientWidth;
  const height = document.body.clientHeight;

  const fetchD3 = async () => {
    const res = await axios.get(
      'http://localhost:8080/yelpdata/fetch-d3-clusters'
    );
    setD3data(res.data);
  };

  };

  return (
    <div className='bg-slate-200 min-h-screen max-h-screen'>
      <div className='flex w-full justify-center flex-col items-center'>
        <div className='w-full mb-8 '>
          <Header />
        </div>
        <div className='flex justify-center items-center w-full'>
          <div className='bg-white h-24 flex justify-center items-center w-[750px] rounded-lg drop-shadow-2xl'>
            <button
              onClick={buildRadialTidyTree}
              className='cursor-pointer bg-indigo-500 px-10 ml-5 shadow-2xl hover:drop-shadow-lg rounded-xl py-2 font-bold text-white hover:bg-indigo-600 '
            >
              Fetch Clusters
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Clusters;
