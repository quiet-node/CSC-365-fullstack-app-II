import axios from 'axios';
import { useEffect, useRef, useState } from 'react';
import Header from '../components/Header';
import {
  cluster,
  hierarchy,
  json,
  linkHorizontal,
  linkRadial,
  linkVertical,
  select,
  tree,
} from 'd3';
import useResizeObserver from '../util/useResizeObserver';

const Clusters = () => {
  const initialState = {
    children: [
      {
        name: '',
        children: [
          {
            name: '',
            value: '',
          },
        ],
      },
    ],
    name: '',
  };
  const [updated, setUpdated] = useState(true);
  const svgRef: any = useRef();
  const wrapperRef = useRef();
  const width = document.body.clientWidth;
  const height = 460 || document.body.clientHeight;
  const radius = width / 2;

  const updateClusters = async () => {
    const data = await json('http://localhost:8080/yelpdata/fetch-d3-clusters');
    updated == true ? setUpdated(false) : setUpdated(true);
    console.log(data);
    if (data != null) {
      const svg = select(svgRef.current);
      const root = hierarchy(data);
      const treeLayout = tree().size([width, height]);
      treeLayout(root);

      console.log(root.descendants());
      console.log(root.links());

      // links generator
      const linksGenerator: any = linkVertical()
        .x((node: any) => node.x)
        .y((node: any) => node.y);

      // nodes
      svg
        .selectAll('.node')
        .data(root.descendants())
        .join('circle')
        .attr('class', 'node')
        .attr('r', 3)
        .attr('fill', '#000')
        .attr('cx', (node: any) => node.x)
        .attr('cy', (node: any) => node.y);

      // links
      svg
        .selectAll('.link')
        .data(root.links())
        .join('path')
        .attr('class', 'link')
        .attr('fill', 'none')
        .attr('stroke', 'black')
        .attr('d', linksGenerator);

      // labels
      svg
        .selectAll('.label')
        .data(root.descendants())
        .join('text')
        .text((node: any) => node.data.name)
        .attr('class', 'label')
        .attr('text-anchor', 'middle')
        .attr('font-size', 12)
        .attr('x', (node: any) => node.x)
        .attr('y', (node: any) => node.y - 10);
    }
  };

  useEffect(() => {
    updateClusters();
  }, []);

  return (
    <div className='bg-slate-200 min-h-screen max-h-screen'>
      <div className='flex w-full justify-center flex-col items-center h-screen'>
        <div className='w-full mb-8'>
          <Header />
        </div>
        <div className='flex justify-center items-center w-full flex-col h-full'>
          <div className='bg-white h-24 flex justify-center items-center w-[750px] rounded-lg drop-shadow-2xl'>
            <button
              onClick={updateClusters}
              className='cursor-pointer bg-indigo-500 px-10 ml-5 shadow-2xl hover:drop-shadow-lg rounded-xl py-2 font-bold text-white hover:bg-indigo-600'
            >
              Fetch Clusters
            </button>
          </div>
          <div className='bg-slate-300 w-full h-full'>
            <svg ref={svgRef} className='h-full w-full pt-10'></svg>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Clusters;
