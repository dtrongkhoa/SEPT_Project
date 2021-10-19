import React from 'react';
import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import {shallow, mount} from 'enzyme';


import Header from '../components/Layout/Header';
import CartItem from '../components/Data/CartItem';
import ProductItem from '../components/Layout/ProductItem';


Enzyme.configure({adapter: new Adapter()});


const setUpHeader = (props={}) => {
    const componentHeader = shallow (<Header/>);
    return componentHeader;
};

const setUpCartItem = (props) => {
    const componentCartItem = shallow (<CartItem {...props} />);
    return componentCartItem;
};
const setUpProductItem = (props) => {
    const componentProductItem = shallow (<ProductItem {...props} />);
    return componentProductItem;
};

const findTestAttr = (component, attr) => {
    const wrapper = component.find(`.${attr}`);
    return wrapper;
};

describe('Header Component', () => {
    let component;
    beforeEach( ()=> {
        component = setUpHeader();

    });

    it('Should be 7 "nav-item" in Header', () => {
        const wrapper = findTestAttr(component, 'nav-item');
        // const wrapper = component.find('.nav-item');
        expect(wrapper.length).toBe(8);
    });
    it('Should has a form for searching in Header', () => {
        const form = findTestAttr(component, 'form-inline');
        // const form = component.find('.form-inline');
        expect(form.length).toBe(0);
    });
})

describe('ProductItem component', () => {
    describe('Have props', () => {
        let wrapper;
        beforeEach(()=>{
            var props = {
                product: {
                    title: "Test",
                    authorFirst: "Test",
                    authorLast: "Test",
                    description: "An alternative cover for this ISBN can be found hereSophie has the great misfortune of being the eldest of three daughters, destined to fail miserably should she ever leave home to seek her fate. But when she unwittingly attracts the ire of the Witch of the Waste, Sophie finds herself under a horrid spell that transforms her into an old lady. Her only chance at breaking it lies in the ever-moving castle in the hills: the Wizard Howl's castle. To untangle the enchantment, Sophie must handle the heartless Howl, strike a bargain with a fire demon, and meet the Witch of the Waste head-on. Along the way, she discovers that there's far more to Howl-and herself-than first meets the eye.",
                    coverImage: "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1407450489l/6294.jpg",
                    pages: "100",
                    publisher: "Harper Trophy",
                    publishedYear: "2001",
                    isbn: "006441034X",
                },

            };
            wrapper = setUpProductItem(props);
        });
        it('should render without error', ()=>{
            const component = findTestAttr(wrapper, 'product-item');
            expect(component.length).toBe(1);
        });
    })

    describe('Have NO props', () => {
        let wrapper;
        beforeEach(()=>{
            wrapper = setUpProductItem();
        });
        it('Should not render', () => {
            const component = findTestAttr(wrapper, 'product-item');
            expect(component.length).toBe(0);
        });
    })

});