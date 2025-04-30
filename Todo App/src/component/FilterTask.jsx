function FilterTask({ filterTitle, setFilterTitle }) {
    return (
      <div className="filter-container">
        <input
          type="text"
          value={filterTitle}
          onChange={(e) => setFilterTitle(e.target.value)}
          placeholder="Filter tasks by title..."
          className="filter-input"
        />
      </div>
    );
  }
  
  export default FilterTask;